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

export default function MyPage() {
  return (
    <>
      <HeaderMain />
      <SeedLevelBadge />
      <SessakLevelBadge />
      <SmallTreeLevelBadge />
      <TooSmallTreeLevelBadge />
      <TreeLevelBadge />
      <IeumLevenBadge />
      <FruitLevelBadge />

      <FruitLevelIcon />
      <IeumLevenIcon />
      <SeedLevelIcon />
      <SessakLevelIcon />
      <SmallTreeLevelIcon />
      <TooSmallTreeLevelIcon />
      <TreeLevelIcon />

      <TabBar selected={'myPage'} />
    </>
  );
}
