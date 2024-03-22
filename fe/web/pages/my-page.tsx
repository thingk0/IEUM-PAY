import TabBar from '@/stories/TabBar';
import HeaderMain from '@/stories/HeaderMain';
import { badges } from '@/components/icons/LevelBadges';
import { Icons } from '@/components/icons/LevelIcons';
import TearList from '@/components/TearList';

export default function MyPage() {
  return (
    <>
      <HeaderMain />

      <Icons.FruitIcon />

      <Icons.SeedIcon />

      <TearList />

      <TabBar selected={'myPage'} />
    </>
  );
}
