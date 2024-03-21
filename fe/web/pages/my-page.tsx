import TabBar from '@/stories/TabBar';
import HeaderMain from '@/stories/HeaderMain';
import {
  FruitLevelBadge,
  IeumLevenBadge,
  SeedLevelBadge,
  SessakLevelBadge,
  SmallTreeLevelBadge,
  TooSmallTreeLevelBadge,
  TreeLevelBadge,
} from '@/components/icons/LevelBadges';
import {
  FruitLevelIcon,
  IeumLevenIcon,
  SeedLevelIcon,
  SessakLevelIcon,
  SmallTreeLevelIcon,
  TooSmallTreeLevelIcon,
  TreeLevelIcon,
} from '@/components/icons/LevelIcons';
import TearList from '@/components/TearList';

export default function MyPage() {
  return (
    <>
      <HeaderMain />

      <TearList />

      <TabBar selected={'myPage'} />
    </>
  );
}
